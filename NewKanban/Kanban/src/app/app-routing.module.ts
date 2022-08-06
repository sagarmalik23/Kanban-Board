import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddtaskComponent } from './component/addtask/addtask.component';
import { ForgotpasswordComponent } from './component/forgotpassword/forgotpassword.component';
import { HomeComponent } from './component/home/home.component';
import { KanbanBoardComponent } from './component/kanban-board/kanban-board.component';
import { LoginComponent } from './component/login/login.component';
import { NewhomeComponent } from './component/newhome/newhome.component';
import { SignupComponent } from './component/signup/signup.component';
import { UpdateComponent } from './component/update/update.component';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [
  {path:"",component:KanbanBoardComponent},
  {path:"signup",component:SignupComponent},
  {path:"login",component:LoginComponent},
  {path:"home",component:HomeComponent,canActivate:[AuthGuard]},
  {path:"addtask",component:AddtaskComponent,canActivate:[AuthGuard]},
  {path:"update",component:UpdateComponent,canActivate:[AuthGuard]},
  {path:"home.",component:NewhomeComponent,canActivate:[AuthGuard]},
  {path:"forgotpassword",component:ForgotpasswordComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
