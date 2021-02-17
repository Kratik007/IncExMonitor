import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthPageGuard } from './auth-page.guard';
import { DeAuthPageGuard } from './de-auth-page.guard'
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms'
import { MaterialsModule } from './materials/materials.module'
import { LoginRegisterComponent } from './login-register/login-register.component'
import { LoginComponent } from './login-register/login.component';
import { RegisterComponent } from './login-register/register.component'
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RsidebarComponent } from './rsidebar/rsidebar.component';
import { UpprofileComponent } from './upprofile/upprofile.component';
import { ExpensescategComponent } from './rsidebar/expensescateg.component';
import { IncomecategComponent } from './rsidebar/incomecateg.component';
import { IncomeComponent } from './rsidebar/income.component';
import { ExpensesComponent } from './rsidebar/expenses.component';
import { ChashbookComponent } from './rsidebar/chashbook.component';
import { BankbookComponent } from './rsidebar/bankbook.component';
import { DaybookComponent } from './rsidebar/daybook.component';
import { BalancesheetComponent } from './rsidebar/balancesheet.component';
import {AppRoutingModule} from './app-routing.module';
import { AuthenticationService } from './service/authentication.service';
import { DatabaseService } from './service/database.service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { InterceptorService } from './RequestInterceptor/interceptor.service';
@NgModule({

    declarations: [
        AppComponent,
        LoginRegisterComponent,
        LoginComponent,
        RegisterComponent,
        ToolbarComponent,
        RsidebarComponent,
        UpprofileComponent,
        ExpensescategComponent,
        IncomecategComponent,
        IncomeComponent,
        ExpensesComponent,
        ChashbookComponent,
        BankbookComponent,
        DaybookComponent,
        BalancesheetComponent,
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,    
        MaterialsModule,
        ReactiveFormsModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule
    ],
    exports: [
        MaterialsModule,
    ],
    providers: [
        AuthPageGuard,
        DeAuthPageGuard,
        AuthenticationService,
        DatabaseService,
        {
            provide : HTTP_INTERCEPTORS,
            useClass: InterceptorService,
            multi   : true,
          },
    ],
    bootstrap: [
        AppComponent,
    ]
})
export class AppModule { }
