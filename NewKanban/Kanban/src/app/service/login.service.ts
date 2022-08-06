import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../class/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private login :string="";
  isLoggedIn: boolean = false;
  redirectUrl: string = "";

  constructor(private Http:HttpClient) {
    this.login="http://localhost:64200/user/login";
   }
   loginCheck(data:Login){
    return this.Http.post<any>(this.login,data)
   }
   forgotPassword(data:string){
    return this.Http.get<any>("http://localhost:64200/user/forgotPassword/"+data);
   }
   logout(){
    window.localStorage.clear();
   }
   retrieveImage(emailId:any){
    return this.Http.get<any>("http://localhost:65001/userBoard/getprofile/"+emailId);
  }
}
