import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../Entities/User';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

   constructor(private auth:AuthenticationService,private http:HttpClient) {
    
   }

   registerUser(user:User){
    return this.http.post(`${environment.URL}reg-user`,user).toPromise();
   }

   getExpenseCategoryById(id){
     return this.http.get<string>(`${environment.URL}expense-categories/${id}`,{headers:new HttpHeaders({'responseType':'json'})}).toPromise();
   }

   getAllExpenseCategories(){
     return this.http.get<string>(`${environment.URL}user-expense-categories/${this.auth.getAuthenticatedUserId()}`,{headers:new HttpHeaders({'responseType':'json'})}).toPromise();
   }

   saveExpenseCategory(expenseCateg){
     return this.http.post<string>(`${environment.URL}expense-categories`,expenseCateg).toPromise();
   }

   updateExpenseCategory(expenseCateg){
     return this.http.put(`${environment.URL}expense-categories`,expenseCateg).toPromise();
   }

   deleteExpenseCategor(id){
     return this.http.delete(`${environment.URL}expense-category/${id}`).toPromise();
   }
   /////////
   
   getIncomeCategoryById(id){
    return this.http.get<string>(`${environment.URL}income-categories/${id}`,{headers:new HttpHeaders({'responseType':'json'})}).toPromise();
    }

  getAllIncomeCategories(){
    return this.http.get<string>(`${environment.URL}user-income-categories/${this.auth.getAuthenticatedUserId()}`,{headers:new HttpHeaders({'responseType':'json'})}).toPromise();
  }

  saveIncomeCategory(incomeCateg){
    return this.http.post<string>(`${environment.URL}income-categories`,incomeCateg).toPromise();
  }

  updateIncomeCategory(incomeCateg){
    return this.http.put(`${environment.URL}income-categories`,incomeCateg).toPromise();
  }

  deleteIncomeCategor(id){
    return this.http.delete(`${environment.URL}income-category/${id}`).toPromise();
  }

  saveExpenseDetails(expense){
    return this.http.post(`${environment.URL}expense`,expense).toPromise();
  }

  saveIncomeDetails(income){
    return this.http.post(`${environment.URL}income`,income).toPromise();
  }

  getAllBankBookDetails(id){
    return this.http.get(`${environment.URL}bank-book/${id}`).toPromise();
  }

  getAllCashBookDetails(id){
    return this.http.get(`${environment.URL}cash-book/${id}`).toPromise();
  }

  getBankBookBetweenDates(id,fromDate,toDate){
    return this.http.get(`${environment.URL}bank-book/${id}/${fromDate}/${toDate}`).toPromise();
  }

  getCashBookBetweenDates(id,fromDate,toDate){
    return this.http.get(`${environment.URL}cash-book/${id}/${fromDate}/${toDate}`).toPromise();
  }

  getAllDayBookDetails(id){
    return this.http.get(`${environment.URL}day-book/${id}`).toPromise();
  }

  getDayBookDetailsBetweenDates(id,fromDate,toDate){
    return this.http.get(`${environment.URL}day-book/${id}/${fromDate}/${toDate}`).toPromise();
  }

  getBalanceSheetByUsername(id){
    return this.http.get(`${environment.URL}balance-sheet/${id}`).toPromise();
  }

  getBalanceSheetBetweenDates(id,dateFrom,dateTo){
    return this.http.get(`${environment.URL}balance-sheet/${id}/${dateFrom}/${dateTo}`).toPromise();
  }

  updateUser(user){
    return this.http.put(`${environment.URL}update-user`,user).toPromise();
  }

  updateUserWithEmail(user){
    return this.http.put(`${environment.URL}update-user-email`,user).toPromise();
  }

  getUser(username){
    return this.http.get(`${environment.URL}user/${username}`).toPromise();
  }

  forgotPassword(user){
    return this.http.put(`${environment.URL}update-password`,user).toPromise();
  }
}
