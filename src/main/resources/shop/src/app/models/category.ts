export class Category{
    private name:string;
    private subcategories:[];
    constructor(){
    }

    public get getName():string{
        return this.name;
    }

    public get getSubcategories():[]{
        return this.subcategories;
    }

    public set setName(name:string){
        this.name = name;
    }

    public set setSubcategories(subcategories:[]){
        this.subcategories = subcategories;
    }

}