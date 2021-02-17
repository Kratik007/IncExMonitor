export class IncomeCateg{
    constructor(public incomeCategoryId:number,public incomeCategoryName:string,public incomeCategoryDetails:string,public username:string){
        this.incomeCategoryId=incomeCategoryId;
        this.incomeCategoryName=incomeCategoryName;
        this.incomeCategoryDetails=incomeCategoryDetails;
        this.username=username;
    }
    setIncomeCategoryId(id){
        this.incomeCategoryId=id;
    }
}