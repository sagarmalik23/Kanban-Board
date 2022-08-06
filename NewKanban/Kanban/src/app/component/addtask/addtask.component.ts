import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { dateValidators } from 'src/app/class/date-validators';
import { KanbanboardService } from 'src/app/service/kanbanboard.service';
import * as alertify from 'alertifyjs';
import { HttpClient } from '@angular/common/http';
import { Mail } from 'src/app/mail';

@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {
  msg: any;

  constructor(private kanbanService:KanbanboardService, private route:ActivatedRoute, private router:Router, private http: HttpClient) { }
  task:any;


    AddTask=new FormGroup({
    // taskId:new FormControl('',[Validators.required]),
    toDo:new FormControl('',[Validators.required]),
    dateAssign:new FormControl((new Date()).toISOString().substring(0,10)),
    deadline:new FormControl('',[Validators.required,dateValidators]),
    priority:new FormControl('')
  });

  todayDate=(new Date()).toISOString().substring(0,10);

  ngOnInit(): void {
  }

  addTask(){
    this.task=this.AddTask.value;
    console.log(this.AddTask.value);
    this.kanbanService.saveTaskForUser(this.task).subscribe(
      response=>{
        console.log(response);
        this.msg = this.task.toDo + " Is Added";
          let object: Mail = {
            "recipient": window.localStorage.getItem("email"),
            "msgBody": this.msg,
            "subject": "Your Task is Added"
          }
          this.http.post<any>("http://localhost:9090/sendMail", object)
            .subscribe(
              (success: any) => {
                // console.log(success);
                alertify.success("Notification Sent");
              }
            );
        this.router.navigate(['/home']);
      },error=>{
          console.log("error "+error)
      }
    )
  }

  // get taskId(){
  //   return this.AddTask.get('taskId')
  //  }
   get toDo(){
    return this.AddTask.get('toDo')
   }
   get dateAssign(){
    return this.AddTask.get('dateAssign')
   }
   get deadline(){
    return this.AddTask.get('deadline')
   }
   get priority(){
    return this.AddTask.get('priority')
   }
  

}
