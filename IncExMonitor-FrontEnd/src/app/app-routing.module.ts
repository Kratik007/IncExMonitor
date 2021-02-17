import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from "./login-register/register.component";
import { LoginComponent } from './login-register/login.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { AuthPageGuard } from './auth-page.guard';
import { DeAuthPageGuard } from './de-auth-page.guard';
import { RsidebarComponent } from './rsidebar/rsidebar.component';
import { UpprofileComponent } from './upprofile/upprofile.component';
import { ExpensescategComponent } from './rsidebar/expensescateg.component';
import { IncomecategComponent } from './rsidebar/incomecateg.component';
import { IncomeComponent } from './rsidebar/income.component';
import { ExpensesComponent } from './rsidebar/expenses.component';
import { BankbookComponent } from './rsidebar/bankbook.component';
import { ChashbookComponent } from './rsidebar/chashbook.component';
import { DaybookComponent } from './rsidebar/daybook.component';
import { BalancesheetComponent } from './rsidebar/balancesheet.component';
const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'dashboard', component: ToolbarComponent,
    children: [
      {
        path: 'menu', component: RsidebarComponent, children: [
          { path: 'expensecategory', component: ExpensescategComponent },
          { path: 'incomecategory', component: IncomecategComponent },
          { path: 'income', component: IncomeComponent },
          { path: 'expenses', component: ExpensesComponent },
          { path: 'bankbook', component: BankbookComponent },
          { path: 'cashbook', component: ChashbookComponent },
          { path: 'daybook', component: DaybookComponent },
          { path: 'balancesheet', component: BalancesheetComponent }
        ],
      },
      { path: 'update', component: UpprofileComponent }
    ], canActivate:[AuthPageGuard], canDeactivate: [DeAuthPageGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
