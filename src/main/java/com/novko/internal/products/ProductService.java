package com.novko.internal.products;

import com.novko.api.exception.CustomFileNameAlreadyExistsException;
import com.novko.api.exception.CustomResourceNotFoundException;
import com.novko.internal.categories.CategoryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    //linux server: folder za slike, za linux /
//    private static final String ROOT_PATH_ON_DISK = "/home/opc/novko/images";

    //windows folder za slike, za windows \\
    private static final String ROOT_PATH_ON_DISK = "C:\\images";

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Transactional
    public void deleteByCode(String code) throws IOException {
        Product product = productRepository.findByCode(code);
        productRepository.deleteByCode(code);
        Path productDirectory = Paths.get(ROOT_PATH_ON_DISK).resolve(product.getId().toString());
        FileUtils.deleteDirectory(new File(productDirectory.toString()));
    }

    //proveri da li product postoji u cart !!!! uradi !!!
    @Transactional
    public void deleteById(Long id) throws IOException {
        productRepository.deleteById(id);
        Path productDirectory = Paths.get(ROOT_PATH_ON_DISK).resolve(id.toString());
        FileUtils.deleteDirectory(new File(productDirectory.toString()));
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.getOne(productId);
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


    //proveri sta se tacno salje od podataka
    @Transactional
    public Product save(String name, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEur, Integer quantity, String subcategoryName) {
        Product product = new Product();
        product.setEnabled(Boolean.TRUE);

        if (name != null && !name.isEmpty()) {
            product.setName(name);
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
    public Product update(Long id, String name, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEur, Integer quantity, Boolean enabled, String subcategoryName) {
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
            throw new CustomResourceNotFoundException("Valid file type");
        }


        String fileName = multipartFile.getOriginalFilename();
        Path imagePath = productDirectory.resolve(fileName);

        if (Files.exists(imagePath)) {
            throw new CustomFileNameAlreadyExistsException("File with that name already exists");
        }

        File imageFile = new File(imagePath.toString()); //image file with file name

        multipartFile.transferTo(imageFile);

        Product product = optionalProduct.get();
        product.getImages().add(imagePath.toString());
        Product productDb = productRepository.save(product);

        return productDb;
    }

    @Transactional
    public Product deleteImage(Long productId, String fileName) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
//        String target = ROOT_PATH_ON_DISK + "/" + productId + "/" + fileName;

        if (!optionalProduct.isPresent()) {
            return null;
        }

        File directory = new File(ROOT_PATH_ON_DISK + "\\" + productId);
        File[] files = directory.listFiles();
        for (File f : files) {
            String fn = f.getName();
            String fname = fn.substring(0, fn.lastIndexOf("."));
            if (fname.equals(fileName)) {
                Files.deleteIfExists(f.toPath());
                break;
            }
        }

        Product product = optionalProduct.get();
        Iterator<String> images = product.getImages().iterator();

        kraj:
        while (images.hasNext()) {
            String image = images.next();
            int index = image.lastIndexOf("\\");
            String imageName = image.substring(index + 1);
            String imageFileName = imageName.substring(0, imageName.lastIndexOf("."));


            if (imageFileName.equals(fileName)) {
                images.remove();
                break kraj;
            }
        }

        return productRepository.save(product);
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
