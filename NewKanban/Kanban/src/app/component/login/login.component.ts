import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from 'src/app/class/login';
import { LoginService } from 'src/app/service/login.service';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login!:Login;

  constructor(private loginservice:LoginService, private route:ActivatedRoute, private router:Router) {
  
   }


  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    "emailId": new FormControl('',[Validators.required]),
    "password": new FormControl('',[Validators.required])
  });


  // loginCheck() {
  //   this.login = this.loginFormGroup.value;
  //   this.loginservice.loginCheck(this.login).subscribe(
  //     success => {
  //       console.log(success);
  //       window.localStorage.setItem("token", success.token);
  //       alert("login success");
  //     },
  //     error => {
  //       console.log(error);
  //       alert("Unauthorised User")
  //     });
  // }

  loginCredential(){
    window.localStorage.clear();
    this.login=this.loginForm.value;
    console.log(this.login)
  this.loginservice.loginCheck(this.login).subscribe(
    success=>{
      console.log(success)
      alertify.success("WELCOME. You are Logged In "+this.login.emailId);
      window.localStorage.setItem('email',this.login.emailId);
      window.localStorage.setItem("token", success.Token);
      window.localStorage.setItem("picUrl",success.picUrl);
      this.router.navigate(['/home']);
    },
    error => { console.log(error);
    alertify.error('Username or Password Did Not Match.');
    }
  )
  };
  

  //  get emailId(){
  //   return this.loginForm.get('emailId')
  //  }
  //  get password(){
  //   return this.loginForm.get('password')
  //  }

  }

