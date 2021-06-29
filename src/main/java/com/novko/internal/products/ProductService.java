package com.novko.internal.products;

import com.novko.api.exception.CustomFileNameAlreadyExistsException;
import com.novko.api.exception.CustomResourceNotFoundException;
import com.novko.api.request.ProductFilter;
import com.novko.api.request.Query;
import com.novko.api.request.SubcategoryProductsFilter;
import com.novko.internal.cart.CartService;
import com.novko.internal.categories.CategoryService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    //linux server: folder za slike, za linux /
//    private static final String ROOT_PATH_ON_DISK = "/home/opc/novko/images";
//    private static final String LOCALHOST = "http://158.101.160.28:8080/images";


    //windows
    private static final String ROOT_PATH_ON_DISK = "C:\\images";
    private static final String LOCALHOST = "http://localhost:8080/images";


    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final CartService cartService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, CartService cartService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @Transactional
    public void deleteByCode(String code) throws IOException {
        Product product = productRepository.findByCode(code);
        if (product == null) {
            return;
        }

        if( cartService.isProductExistsByCode(code) ) {
            product.setEnabled(false);
            productRepository.save(product);
            Path productDirectory = Paths.get(ROOT_PATH_ON_DISK).resolve(product.getId().toString());
            FileUtils.deleteDirectory(new File(productDirectory.toString()));
        } else {
            productRepository.deleteByCode(code);
            Path productDirectory = Paths.get(ROOT_PATH_ON_DISK).resolve(product.getId().toString());
            FileUtils.deleteDirectory(new File(productDirectory.toString()));
        }
    }

//    private void setProductActiveStatus(String code, Boolean disabled) {
//        Product product = productRepository.findByCode(code);
//        product.setEnabled(disabled);
//        productRepository.save(product);
//    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllOrFiltered(Query query) {
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getSize(),
                Sort.by(new Sort.Order(Sort.Direction.fromString(query.getSortDirection().toUpperCase()), query.getSortProperty(), Sort.NullHandling.NULLS_LAST)));
        ProductFilter filter = (ProductFilter) query.getFilter();

        Predicate predicate = buildPredicate(filter);

        Page<Product> products;

        if (predicate != null) {
            products = productRepository.findAll(predicate, pageRequest);
        } else {
            products = productRepository.findAll(pageRequest);
        }

        if (products == null) {
            return null;
        }

        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> findSubcategoryAllOrFilteredProducts(Query query) {
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getSize(),
                Sort.by(new Sort.Order(Sort.Direction.fromString(query.getSortDirection().toUpperCase()), query.getSortProperty(), Sort.NullHandling.NULLS_LAST)));
        SubcategoryProductsFilter filter = (SubcategoryProductsFilter) query.getFilter();

        Predicate predicate = buildPredicateSubcategoryProducts(filter);

        Page<Product> products;

        if (predicate != null) {
            products = productRepository.findAll(predicate, pageRequest);
        } else {
            products = productRepository.findAll(pageRequest);
        }

        if (products == null) {
            return null;
        }

        return products;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllWhereSubcategoryIsNull() {
        return productRepository.findBySubcategoryIsNull();
    }

    @Transactional
    public Product save(String name, String nameSr, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEur, Integer quantity, String subcategoryName) {
        Product product = new Product();
        product.setEnabled(Boolean.TRUE);
        product.setCreatedDate(OffsetDateTime.now(ZoneOffset.UTC));

        if (name != null && !name.isEmpty()) {
            product.setName(name);
        }
        if (nameSr != null && !nameSr.isEmpty()) {
            product.setNameSr(nameSr);
        }
        if (code != null && !code.isEmpty()) {
            product.setCode(code);
        }
        if (brand != null && !brand.isEmpty()) {
            product.setBrand(brand);
        }
        if (description != null && !description.isEmpty()) {
            product.setDescription(description);
        }
        if (descriptionSr != null && !descriptionSr.isEmpty()) {
            product.setDescriptionSr(descriptionSr);
        }
        if (amountDin != null) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        Product productDb = productRepository.save(product);
        categoryService.addProductToSubcategory(subcategoryName, productDb);
        return productDb;
    }

    @Transactional
    public Product update(Long id, String name, String nameSr, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEur, Integer quantity, Boolean enabled, String subcategoryName) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new CustomResourceNotFoundException("Product is not in database");
        }

        Product product = optionalProduct.get();

        if (enabled != null && !enabled.equals(product.getEnabled())) {
            product.setEnabled(enabled);
        }
        if (name != null && !name.isEmpty() && !name.equals(product.getName())) {
            product.setName(name);
        }
        if (nameSr != null && !nameSr.isEmpty() && !nameSr.equals(product.getNameSr())) {
            product.setNameSr(nameSr);
        }
        if (code != null && !code.isEmpty() && !code.equals(product.getCode())) {
            product.setCode(code);
        }
        if (brand != null && !brand.isEmpty() && !brand.equals(product.getBrand())) {
            product.setBrand(brand);
        }
        if (description != null && !description.isEmpty() && !description.equals(product.getDescription())) {
            product.setDescription(description);
        }
        if (descriptionSr != null && !descriptionSr.isEmpty() && !descriptionSr.equals(product.getDescriptionSr())) {
            product.setDescriptionSr(descriptionSr);
        }
        if (amountDin != null && !amountDin.equals(product.getAmountDin())) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null && !amountEur.equals(product.getAmountEuro())) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null && !quantity.equals(product.getQuantity())) {
            product.setQuantity(quantity);
        }

        if (subcategoryName != null || !subcategoryName.isEmpty()) {
            categoryService.addProductToSubcategory(subcategoryName, product);
        }

        Product productDb = productRepository.save(product);
        return productDb;
    }

    @Transactional
    public Product saveImageOnDisk(Long productId, MultipartFile multipartFile) throws IOException, CustomFileNameAlreadyExistsException, CustomResourceNotFoundException {

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new CustomResourceNotFoundException("Product doesn't exist in database");
        }

        Path path = Paths.get(ROOT_PATH_ON_DISK);
        if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(path);
        }

        Path productDirectory = path.resolve(productId.toString());
        if (Files.notExists(productDirectory, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(productDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long count;
        try (Stream<Path> files = Files.list(Paths.get(productDirectory.toString()))) {
            count = files.count();
        }

        if (count > 3) {
            throw new CustomResourceNotFoundException("More than 4 images saved for Product");
        }

        Set<String> checkFileType = Stream.of("image/jpeg", "image/png")
                .collect(Collectors.toCollection(HashSet::new));

        if (!checkFileType.contains(multipartFile.getContentType())) {
            throw new CustomResourceNotFoundException("Invalid file type");
        }


        String fileName = multipartFile.getOriginalFilename(); //slika.png
        Path imagePath = productDirectory.resolve(fileName); // c:/images/product_id/slika.png

        String imagePathString = imagePath.toString();

        //uradi!!!!
        String deleteBackslashes = imagePathString.replace("\\", "/"); //samo za windows !!!!

        //windows
        String link = deleteBackslashes.replace("C:/images", LOCALHOST); //zamenjuje putanju sa linkom, samo za windows!!!!

        //linux
//        String link = imagePathString.replace(ROOT_PATH_ON_DISK, LOCALHOST); //zamenjuje putanju sa linkom

        Product product = optionalProduct.get();

        if (Files.exists(imagePath) && product.getImages().indexOf(link) != -1) {
            throw new CustomFileNameAlreadyExistsException("File with that name already exists");
        }

        if (product.getImages().size() > 4) {
            throw new CustomFileNameAlreadyExistsException("You already uploaded 4 images");
        }

        File imageFile = new File(imagePath.toString()); //kreira file na disku c:/images/product_id/slika.png
        multipartFile.transferTo(imageFile); //cuva sliku na hard disku u folderu c:/images/1/slika.jpg

        product.getImages().add(link); //cuva link slike, ne path do hard diska (zbog frontenda)
        Product productDb = productRepository.save(product);

        return productDb;


//        File imageFile = new File(imagePath.toString()); //kreira file na disku c:/images/product_id/slika.png
//
//        multipartFile.transferTo(imageFile); //cuva sliku na hard disku u folderu c:/images/1/slika.jpg
//
//        Product product = optionalProduct.get();
//        product.getImages().add(link); //cuva link slike, ne path do hard diska (zbog frontenda)
//
//        Product productDb = productRepository.save(product);
//
//        return productDb;

        //stari kod
//       Optional<Product> optionalProduct = productRepository.findById(productId);
////        String target = ROOT_PATH_ON_DISK + "/" + productId + "/" + fileName;
//
//        if (!optionalProduct.isPresent()) {
//            return null;
//        }
//
//        File directory = new File(ROOT_PATH_ON_DISK + "\\" + productId);
//        File[] files = directory.listFiles();
//        for (File f : files) {
//            String fn = f.getName();
//            String fname = fn.substring(0, fn.lastIndexOf("."));
//            if (fname.equals(fileName)) {
//                Files.deleteIfExists(f.toPath());
//                break;
//            }
//        }
//
//        Product product = optionalProduct.get();
//        Iterator<String> images = product.getImages().iterator();
//
//        kraj:
//        while (images.hasNext()) {
//            String image = images.next();
//            int index = image.lastIndexOf("\\");
//            String imageName = image.substring(index + 1);
//            String imageFileName = imageName.substring(0, imageName.lastIndexOf("."));
//
//
//            if (imageFileName.equals(fileName)) {
//                images.remove();
//                break kraj;
//            }
//        }
//
//        return productRepository.save(product);
    }

    @Transactional
    public Product deleteImage(Long productId, String fileName) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
//        String target = ROOT_PATH_ON_DISK + "/" + productId + "/" + fileName;

        if (!optionalProduct.isPresent()) {
            return null;
        }

        //windows
        String pathOnDisk = fileName.replace(LOCALHOST, "C:/images" ); //za windows

        //linux
//        String pathOnDisk = fileName.replace(LOCALHOST, ROOT_PATH_ON_DISK );

        //windows
        String directoryWindows = pathOnDisk.replace("/", "\\"); //za windows path
        String dir = directoryWindows.substring(0, directoryWindows.lastIndexOf("\\"));


        //linux
//        String dir = pathOnDisk.substring(0, pathOnDisk.lastIndexOf("/"));

        File directory = new File(dir); //dir
        File[] files = directory.listFiles();
        for (File f : files) {
            String fname = f.getName(); //porse.jpg
            String linkFilename = fileName.substring(fileName.lastIndexOf("/")+1); //porse.jpg
//            String fname = fn.substring(0, fn.lastIndexOf(".")); //porse
            if (fname.equals(linkFilename)) {
                Files.deleteIfExists(f.toPath());
                break;
            } //filename locahost:8080/images/2/slika.porse.jpg
        }
//        for (File f : files) {
//            String fn = f.getName();
//            String fname = fn.substring(0, fn.lastIndexOf(".")); //porse
//            if (fname.equals(fileName)) {
//                Files.deleteIfExists(f.toPath());
//                break;
//            } //filename locahost:8080/images/2/slika.porse.jpg
//        }

        Product product = optionalProduct.get();
        Iterator<String> images = product.getImages().iterator();

        kraj:
        while (images.hasNext()) {
            String image = images.next(); //localhost link ceo
//            int index = image.lastIndexOf("\\");
//            String imageName = image.substring(index + 1);
//            String imageFileName = imageName.substring(0, imageName.lastIndexOf("."));


            if (image.equals(fileName)) {
                images.remove();
                break kraj;
            }
        }
//        kraj:
//        while (images.hasNext()) {
//            String image = images.next(); //localhost link ceo
//            int index = image.lastIndexOf("\\");
//            String imageName = image.substring(index + 1);
//            String imageFileName = imageName.substring(0, imageName.lastIndexOf("."));
//
//
//            if (imageFileName.equals(fileName)) {
//                images.remove();
//                break kraj;
//            }
//        }

        return productRepository.save(product);
//stari kod
//        Optional<Product> optionalProduct = productRepository.findById(productId);
////        String target = ROOT_PATH_ON_DISK + "/" + productId + "/" + fileName;
//
//        if (!optionalProduct.isPresent()) {
//            return null;
//        }
//
//        File directory = new File(ROOT_PATH_ON_DISK + "\\" + productId);
//        File[] files = directory.listFiles();
//        for (File f : files) {
//            String fn = f.getName();
//            String fname = fn.substring(0, fn.lastIndexOf("."));
//            if (fname.equals(fileName)) {
//                Files.deleteIfExists(f.toPath());
//                break;
//            }
//        }
//
//        Product product = optionalProduct.get();
//        Iterator<String> images = product.getImages().iterator();
//
//        kraj:
//        while (images.hasNext()) {
//            String image = images.next();
//            int index = image.lastIndexOf("\\");
//            String imageName = image.substring(index + 1);
//            String imageFileName = imageName.substring(0, imageName.lastIndexOf("."));
//
//
//            if (imageFileName.equals(fileName)) {
//                images.remove();
//                break kraj;
//            }
//        }
//
//        return productRepository.save(product);
    }

    private Predicate buildPredicate(ProductFilter filter) {
        List<Predicate> expressions = new LinkedList<>();
        if (filter != null) {
            if (filter.isActive() != null) {
                expressions.add(
                        QProduct.product.enabled.eq(filter.isActive()));
            }
            if (filter.getNamePart() != null && !filter.getNamePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.name.containsIgnoreCase(filter.getNamePart()));
            }
            if (filter.getNamePartSr() != null && !filter.getNamePartSr().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.nameSr.containsIgnoreCase(filter.getNamePartSr()));
            }
            if (filter.getCodePart() != null && !filter.getCodePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.code.containsIgnoreCase(filter.getCodePart()));
            }
        }
        return ExpressionUtils.allOf(expressions);
    }

    private Predicate buildPredicateSubcategoryProducts(SubcategoryProductsFilter filter) {
        List<Predicate> expressions = new LinkedList<>();
        if (filter != null) {
            if (filter.getActive() != null) {
                expressions.add(
                        QProduct.product.enabled.eq(filter.getActive()));
            }
            if (filter.getSubcategoryName() != null && !filter.getSubcategoryName().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.subcategory.name.eq(filter.getSubcategoryName()));
            }
            if (filter.getProductNamePart() != null && !filter.getProductNamePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.name.containsIgnoreCase(filter.getProductNamePart()));
            }
            if (filter.getProductNamePartSr() != null && !filter.getProductNamePartSr().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.nameSr.containsIgnoreCase(filter.getProductNamePartSr()));
            }
            if (filter.getProductCodePart() != null && !filter.getProductCodePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.code.containsIgnoreCase(filter.getProductCodePart()));
            }
        }
        return ExpressionUtils.allOf(expressions);
    }

}


//        Product product;
//        if (optionalProduct.isPresent()) {
//            product = optionalProduct.get();
//            Iterator<String> images = product.getImages().iterator();
//
//            kraj:
//            while (images.hasNext()) {
//                String image = images.next();
//                String imageFileName = image.substring((image.lastIndexOf("/") + 1), image.lastIndexOf("."));
//
//                String e = target.substring((target.lastIndexOf("/") + 1));
//                if (imageFileName.equals(e)) {
//                    images.remove();
//                    break kraj;
//                }
//            }
//
//            Product productDb = productRepository.save(product);
//
//            Path path = Paths.get(ROOT_PATH_ON_DISK);
//            Path directoryName = path.resolve(productId.toString());
//            File directory = new File(directoryName.toString());
//
//            String[] files = directory.list();
//            for (String fName : files) {
//                String truncFName = fName.substring(0, fName.lastIndexOf("."));
//                if (truncFName.startsWith(fileName)) {
//                    String d = ROOT_PATH_ON_DISK + "/" + productId + "/" + fName;
//                    new File(d).delete();
//                }
//            }
//
//            return productDb;
//        }
//
//        return null;


//            File[] files = directory.listFiles(new FilenameFilter() {
//                @Override
//                public boolean accept(File dir, String name) {
//                    return name.matches(fileName);
//                }
//            });
//
//            for (File i : files) {
//                i.delete();
//            }
