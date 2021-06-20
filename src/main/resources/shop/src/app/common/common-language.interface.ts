export interface CommonLanguageInterface {
  siteTitle: string;
  edit: string;
  seen: string;
  download: string;
  home: string;
  delete: string;
  deleteSubcategory: string;
  deleteCategory: string;
  productInformation: string;
  viewProduct: string;
  productCode: string;
  productDescription: string;
  productDeleted: string;
  amount: string;
  priceAmount: string;
  currency: string;
  cart: string;
  addToCart: string;
  total: string;
  totalRebate: string;
  totalWithTax: string;
  quantity: string;
  price: string;
  checkout: string;
  logout: string;
  login: string;
  username: string;
  register: string;
  password: string;
  passwordAgain: string;
  addProduct: string;
  addProductDescription: string;
  editProductSubtitle: string;
  products: string;
  users: string;
  productsSubtitle: string;
  productsOrdered: string;
  usersSubtitle: string;
  userRegistered: string;
  addUser: string;
  categories: string;
  categoriesSubtitle: string;
  addCategory: string;
  addCategorySubtitle: string;
  addSubcategory: string;
  addSubcategorySubtitle: string;
  subcategories: string;
  subcategoriesSubtitle: string;
  subcategory: string;
  subcategoryEditedName: string;
  subcategoryDeleted: string;
  subcategoryAdded: string;
  category: string;
  dropFiles: string;
  required: string;
  welcome: string;
  errorUploadMultiple: string;
  errorValidData: string;
  errorLogin: string;
  errorValidEmail: string;
  errorPasswordMatch: string;
  errorQuantity: string;
  errorMin: string;
  errorMax: string;
  errorSthWrong: string;
  errorProductsNotOrdered: string;
  errorSessionExpired: string;
  name: string;
  categoryName: string;
  categoryEditedName: string;
  categoryDeleted: string;
  categoryAdded: string;
  nameSr: string;
  categoryNameSr: string;
  code: string;
  brand: string;
  description: string;
  descriptionSr: string;
  amountDin: string;
  amountEuro: string;
  id: string;
  actions: string;
  active: string;
  inactive: string;
  role: string;
  rebate: string;
  company: string;
  admin: string;
  user: string;
  language: string;
  orders: string;
  allOrders: string;
  order: string;
  orderSingle: string;
  ordersSubtitle: string;
  orderMarkAsSeen: string;
  orderDeleted: string;
  registerUser: string;
  registerUserSubtitle: string;
  firma: string;
  grad: string;
  mb: string;
  pib: string;
  rabat: string;
  ulica: string;
  areYouSure: string;
  areYouSureDescription: string;
  confirm: string;
  cancel: string;
  imageAdded: string;
  productAdded: string;
  productEdited: string;
  addedToCart: string;
  addedToCartName: string;
  removedFromCartName: string;
  noItem: {
    [key in NoItem]: NoItemTranslation
  };
}

export interface NoItemTranslation {
  icon: string;
  title: string;
  description: string;
}

export enum NoItem {
  orders,
  users,
  categories,
  subcategories,
  products,
  productsHome,
}
