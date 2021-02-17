export class User{
    username:String;
    password:String;
    fullname:String;
    email:String;
    mobile:String;
    constructor(username,password,fullname,email,mobile){
        this.username=username;
        this.password=password;
        this.fullname=fullname;
        this.email=email;
        this.mobile=mobile;
    }
}
export class UserDet{
    userid:String;
    name:String;
    mail:String;
    contact:String;
    constructor(userid,name,mail,contact){
        this.userid=userid;
        this.name=name;
        this.mail=mail;
        this.contact=contact;
    }
}