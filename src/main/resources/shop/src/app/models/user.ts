export class User {

    private username;
    private password;
    private email;

    constructor(){}

    public get getUsername() {
        return this.username;
    }
    public get getPassword() {
        return this.password;
    }
    public get getEmail() {
        return this.email;
    }
    public set setUsername(username) {
        this.username = username;
    }
    public set setPassword(password) {
        this.password = password;
    }
    public set setEmail(email) {
        this.email = email;
    }
}