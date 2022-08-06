import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Signup } from 'src/app/class/signup';
import { SignupService } from 'src/app/service/signup.service';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signup: any;
  selectedFile: any;
  signUpInfo: any;
  userObj: any;
  userlist: any = [];
  message: any;
  url: any;

  constructor(private signupservice: SignupService, private route: ActivatedRoute, private router: Router) { }

  userFormGroup = new FormGroup({
    emailId: new FormControl('', [Validators.required,Validators.pattern("^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z]+(?:\.[a-zA-Z]+)*$")]),
    password: new FormControl('', [Validators.required,Validators.pattern("^(.{6,20})$")]),
    name: new FormControl('', [Validators.required]),
    image: new FormControl('', [Validators.required])
  });


  ngOnInit(): void {
  }

  signupdata() {
    this.signUpInfo = this.userFormGroup.value;
    this.userObj = {
      name: this.userFormGroup.value.name,
      emailId: this.userFormGroup.value.emailId,
      password: this.userFormGroup.value.password,
      image: this.selectedFile
    }
    this.userlist.push(this.userObj)
    let obj = new FormData(
    )
    let data;
    data = obj.append("image", this.userlist[0].image, this.userlist[0].image.name);
    delete this.userlist[0]['image'];
    console.log(data)
    obj.append("input", JSON.stringify(this.userlist[0]));
    console.log(obj.toString)
    let response = this.signupservice.registeruser(obj);
    response.subscribe(
      (success) => {
        this.message = success;
        console.log(this.message);
        alertify.success("User added successfully..please login to continue");
        this.router.navigateByUrl("/login")
      },
      (error) => {
        console.log("invalid")
        alertify.error("User Already Exists")
      });
  }
  imgSave(e: any) {
    this.selectedFile = e.target.files[0]
    var reader = new FileReader()
    reader.readAsDataURL(this.selectedFile)
    reader.onload = (data: any) => {
      this.url = data.target.result
    }
    console.log(this.selectedFile)
  }

  get emailId() {
    return this.userFormGroup.get('emailId')
  }
  get password() {
    return this.userFormGroup.get('password')
  }
  get name() {
    return this.userFormGroup.get('name')
  }
  get image() {
    return this.userFormGroup.get("image")
  }
}

// this.signUpInfo = this.userFormGroup.value;
//           this.userObj={
//             name:this.userFormGroup.value.name,
//             emailId:this.userFormGroup.value.emailId,
//             password:this.userFormGroup.value.password,
//              image:this.selectedFile
//           }
//           this.userlist.push(this.userObj)
//           let obj = new FormData(

//           )
//           let data;
//           data =obj.append("image",this.userlist[0].image,this.userlist[0].image.name);
//           delete this.userlist[0]['image'];
//           console.log(data)
//           obj.append("registration",JSON.stringify(this.userlist[0]));

//           console.log(obj.toString)
//     let response = this.service.signUp(obj);
//     response.subscribe(
//       (success)=>
//       {
//          this.message=success;
//          console.log(this.message);
//          localStorage.setItem("emailId",this.form.value.emailId);

//       },
//       (error)=>{
//           console.log("invalid")
//           alert("user added successfully..please login to continue");
//          this.router.navigateByUrl("/login")
//       });

