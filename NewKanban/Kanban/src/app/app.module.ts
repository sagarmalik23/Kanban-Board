import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignupComponent } from './component/signup/signup.component';
import { LoginComponent } from './component/login/login.component';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { KanbanBoardComponent } from './component/kanban-board/kanban-board.component';
import { UpdateComponent } from './component/update/update.component';
import { AddtaskComponent } from './component/addtask/addtask.component';
import { HomeComponent } from './component/home/home.component';
// import { ListService } from './service/list.service';
import { NewhomeComponent } from './component/newhome/newhome.component';
import { FooterComponent } from './component/footer/footer.component';
import { ForgotpasswordComponent } from './component/forgotpassword/forgotpassword.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    KanbanBoardComponent,
    UpdateComponent,
    AddtaskComponent,
    HomeComponent,
    NewhomeComponent,
    FooterComponent,
    ForgotpasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
