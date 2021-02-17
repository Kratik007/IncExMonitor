export class ExpenseCateg{
    constructor(public expenseCategoryId:number,public expenseCategoryName:string,public expenseCategoryDetails:string,public username:string){
    }
    
  public getExpenseCategoryId(): number {
    return this.expenseCategoryId;
  }
  public getExpenseCategoryName(): string {
    return this.expenseCategoryName;
  }
  public getExpenseCategoryDetails(): string {
    return this.expenseCategoryDetails;
  }
  public getUsername():string {
    return this.username;
  }

    setExpenseCategoryId(id){
        this.expenseCategoryId=id;
    }
}