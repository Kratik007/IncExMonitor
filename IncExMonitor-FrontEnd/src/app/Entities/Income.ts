export class Income{
    constructor(private incomeId:number,private accountName:string,private amount:number,private receiveBy:string,private date:string,private remark:string,private incomeCategoryId:string,private username:string){
        this.incomeId=incomeId;
        this.accountName=accountName;
        this.amount=amount;
        this.receiveBy=receiveBy;
        this.date=date;
        this.remark=remark;
        this.incomeCategoryId=incomeCategoryId;
        this.username=username;
    }
}