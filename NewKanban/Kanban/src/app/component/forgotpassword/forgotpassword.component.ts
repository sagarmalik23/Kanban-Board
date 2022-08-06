import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RESOURCE_CACHE_PROVIDER } from '@angular/platform-browser-dynamic';
import { ActivatedRoute, Router } from '@angular/router';
import { Mail } from 'src/app/mail';
import { LoginService } from 'src/app/service/login.service';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {
  mail: any;
  msg: any;

  constructor(private login: LoginService, private http: HttpClient, private route: ActivatedRoute, private router: Router) { }

  Email = new FormGroup({
    emailId: new FormControl('', [Validators.required]),
  });

  ngOnInit(): void {
  }

  forgotPassword() {
    this.mail = this.Email.value;
    this.login.forgotPassword(this.mail.emailId).subscribe(
      response => {
        this.msg = this.mail.emailId + " Password Is " + response.password;
        let object: Mail = {
          "recipient": this.mail.emailId,
          "msgBody": this.msg,
          "subject": "Your Password for Kanban Board Is.."
        }
        this.http.post<any>("http://localhost:9090/sendMail", object)
          .subscribe(
            success => {
              // console.log(success);
              alertify.success("Notification Sent");
              this.router.navigate(['/login']);
            }, error => {
              console.log("error " + error)
            }
          )
          
       
      }
    );
  }
}


/*
{ fdfd:dfdfd
fdfd:fdfd}

*/