export class Response{
    jwt:string;
    userid:string;
    name:string;
    constructor(jwt,userid,name){
        this.jwt=jwt;
        this.userid=userid;
        this.name=name;
    }
}