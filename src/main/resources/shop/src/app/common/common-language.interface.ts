export interface CommonLanguageInterface {
  actions: string;
  active: string;
  addCategory: string;
  addCategorySubtitle: string;
  addProduct: string;
  addProductDescription: string;
  addSubcategory: string;
  addSubcategorySubtitle: string;
  addToCart: string;
  addUser: string;
  addedToCart: string;
  addedToCartName: string;
  admin: string;
  allOrders: string;
  userOrders: string;
  amount: string;
  amountDin: string;
  amountEuro: string;
  areYouSure: string;
  areYouSureDescription: string;
  brand: string;
  cancel: string;
  cart: string;
  status: string;
  wasSeen: string;
  wasntSeen: string;
  categories: string;
  categoriesSubtitle: string;
  category: string;
  categoryAdded: string;
  categoryDeleted: string;
  categoryEditedName: string;
  categoryName: string;
  categoryNameSr: string;
  checkout: string;
  code: string;
  company: string;
  confirm: string;
  contact: string;
  copy: string;
  currency: string;
  delete: string;
  deleteCategory: string;
  deleteSubcategory: string;
  description: string;
  descriptionSr: string;
  download: string;
  dropFiles: string;
  sortBy: string;
  sortType: string;
  searchProduct: string;
  searchOrder: string;
  enterDateRange: string;
  formatTimeMedium: string;
  edit: string;
  editProductSubtitle: string;
  errorLogin: string;
  errorMax: string;
  errorMin: string;
  errorPasswordMatch: string;
  errorProductsNotOrdered: string;
  errorQuantity: string;
  errorSessionExpired: string;
  errorSthWrong: string;
  errorUploadMultiple: string;
  errorValidData: string;
  errorValidEmail: string;
  firma: string;
  grad: string;
  home: string;
  id: string;
  idNo: string;
  imageAdded: string;
  inactive: string;
  language: string;
  login: string;
  logout: string;
  mb: string;
  menu: string;
  name: string;
  nameSr: string;
  noItem: {
    [key in NoItem]: NoItemTranslation
  };
  order: string;
  orderDeleted: string;
  orderMarkAsSeen: string;
  orderSingle: string;
  orders: string;
  ordersSubtitle: string;
  password: string;
  passwordAgain: string;
  pib: string;
  price: string;
  dateTime: string;
  priceAmount: string;
  productAdded: string;
  productCode: string;
  productDeleted: string;
  productDescription: string;
  productEdited: string;
  productInformation: string;
  products: string;
  productsOrdered: string;
  productsSubtitle: string;
  quantity: string;
  rabat: string;
  rebate: string;
  register: string;
  registerUser: string;
  registerUserSubtitle: string;
  removedFromCartName: string;
  required: string;
  role: string;
  seen: string;
  siteTitle: string;
  subcategories: string;
  subcategoriesSubtitle: string;
  subcategory: string;
  subcategoryAdded: string;
  subcategoryDeleted: string;
  subcategoryEditedName: string;
  taxNo: string;
  total: string;
  totalRebate: string;
  totalWithTax: string;
  ulica: string;
  user: string;
  userRegistered: string;
  username: string;
  users: string;
  usersSubtitle: string;
  viewProduct: string;
  welcome: string;
  paginator: {
    paginatorIntl : {
      itemsPerPageLabel: string;
      nextPageLabel: string;
      previousPageLabel: string;
      firstPageLabel: string;
      lastPageLabel: string;
    },
    dropdown: {
      price: string;
      time: string;
      ascending: string;
      descending: string;
    }
    of: string
  }
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
