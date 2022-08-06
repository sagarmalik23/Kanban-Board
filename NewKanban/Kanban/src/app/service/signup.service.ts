import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Signup } from '../class/signup';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  
  // productinputurl:string="http://localhost:64200/userservice";
  signupurl:string=""
  constructor(private httpclient:HttpClient) { 
  this.signupurl="http://localhost:65001/userBoard/adduser";
  }

  registeruser(data:any){
return this.httpclient.post<any>(this.signupurl,data);
  }
  
}
