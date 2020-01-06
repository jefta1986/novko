export class Subcategory {

    private id;
    private name: string;
    private products: [];
    
    constructor() {
    }

    public get getName(): string {
        return this.name;
    }

    public get getID() {
        return this.id;
    }

    public get getProducts(): [] {
        return this.products;
    }

    public set setName(name: string) {
        this.name = name;
    }

    public set setProducts(products: []) {
        this.products = products;
    }

    public set setID(id) {
        this.id = id;
    }

}