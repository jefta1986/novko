export class Utils{

    static readonly cartArray = 'cartArray';

    static getProductsFromCart(){
        return JSON.parse(localStorage.getItem(Utils.cartArray));
    }

    static addToCart(productName){
        let array = [];
        if(localStorage.getItem(Utils.cartArray) == null){
            array = [];
            array.push(productName);
            localStorage.setItem(Utils.cartArray,JSON.stringify(array));
        }else{
            array = JSON.parse(localStorage.getItem(Utils.cartArray));
            array.push(productName);
            let uniqueArray = array.filter(function(item, pos) {
                return array.indexOf(item) == pos;
            })
            localStorage.setItem(Utils.cartArray,JSON.stringify(uniqueArray));
        }
    }
    
}