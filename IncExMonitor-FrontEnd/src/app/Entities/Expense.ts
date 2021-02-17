export class Expense{
    constructor(private expenseId:number,private accountName:string,private amount:number,private payedBy:string,private date:string,private remark:string,private expenseCategoryId:string,private username:string){
        this.expenseId=expenseId;
        this.accountName=accountName;
        this.amount=amount;
        this.payedBy=payedBy;
        this.date=date;
        this.remark=remark;
        this.expenseCategoryId=expenseCategoryId;
        this.username=username;
    }
}