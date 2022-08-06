import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Kanban } from 'src/app/kanban';
import { KanbanboardService } from 'src/app/service/kanbanboard.service';
import { LoginService } from 'src/app/service/login.service';
import * as alertify from 'alertifyjs';
import { Mail } from 'src/app/mail';
import { HttpClient } from '@angular/common/http';
import { Task } from 'src/app/class/task';


@Component({
  selector: 'app-newhome',
  templateUrl: './newhome.component.html',
  styleUrls: ['./newhome.component.css']
})
export class NewhomeComponent implements OnInit {
  ToDoList: Kanban[] = [];
  Research: Kanban[] = [];
  InProgress: Kanban[] = [];
  Review: Kanban[] = [];
  Completed: Kanban[] = [];
  AllTask: Kanban[] = [];
  count!: Number;
  ToDoCount!:Number;
  ResearchCount!:Number;
  InProgressCount!:Number;
  ReviewCount!:Number;
  CompletedCount!:Number;
  msg: any;
  image: any;
  userImage: any;
  searchTask:any;
  jump:any;
  changeStatus!:Task[];
  task:any;
  

  constructor(private kanbanservice: KanbanboardService, private route:ActivatedRoute, private router:Router
    ,private loginservices:LoginService,private http: HttpClient) {
   }

  ngOnInit(): void {

    this.changeStatus=[
      {value:"Todo",status:0},
      {value:"Research",status:1},
      {value:"In-progress",status:2},
      {value:"Review",status:3},
      {value:"Completed",status:4}
    ];

    this.kanbanservice.getAllTaskForUser().subscribe(result => {
      this.AllTask = result;
      this.count = this.AllTask.length;

      let email=window.localStorage.getItem('email');
      let response=this.loginservices.retrieveImage(email);

      response.subscribe(
        (success)=>{
          this.image=success;
          this.userImage='data:image/jpeg;base64,' +this.image.image;
        }
      )
      console.log("id" + result);
      console.log("length" + this.count);
      this.AllTask.forEach(task => {
        if (task.taskStatus == 0) {
          this.ToDoList.push(task);
        }
        else if (task.taskStatus == 1) {
          //this.ToDoList.pop();
          this.Research.push(task);
        }
        else if (task.taskStatus == 2) {
          this.InProgress.push(task);
        }
        else if (task.taskStatus == 3) {
          this.Review.push(task);
        }
        else if (task.taskStatus == 4) {
          this.Completed.push(task);
        }

      });
      this.ToDoCount=this.ToDoList.length;
      this.ResearchCount=this.Research.length;
      this.InProgressCount=this.InProgress.length;
      this.ReviewCount=this.Review.length;
      this.CompletedCount=this.Completed.length;
      console.log("tL"+this.ToDoCount);
      console.log("RC"+this.ResearchCount);
      console.log("IP"+this.InProgressCount);
      console.log("ReC"+this.ResearchCount);
      console.log("CC"+this.CompletedCount);
    })

  }

  

  // Refresh(){
  //   this.kanbanservice.getAllTaskForUser().subscribe(
  //     data=>{
  //       console.log(data)
  //       this.AllTask=data   
  //     }
  //   )
   

  // updateTask(u:any){
  //   console.log("taskid"+u);
  //   window.localStorage.setItem('taskId',u);
  //   this.router.navigate(['/update'])
  // }

  updateTask(u:Kanban){
    console.log("taskid"+u.taskId ,u.deadline, u.priority, u.toDo);
    window.localStorage.setItem("taskId",(u.taskId).toString());
    window.localStorage.setItem("todo",u.toDo);
    window.localStorage.setItem("deadline",u.deadline);
    window.localStorage.setItem("priority",u.priority);
    this.router.navigate(['/update'])
  }
  deleteTask(u:any){
    this.kanbanservice.deleteTaskForUser(u,u.taskId).subscribe(
      success=>{
        console.log("deleted");
        alertify.success("Successfully Deleted");
        // this.Refresh();
        
        this.router.navigate(['/home']);  
      },error=>{
        console.log("not deleted");
        alertify.error("Not Done")
      }
    )
  }
  moveForward(u:any){

    this.kanbanservice.increaseTaskStatus(u).subscribe(
      success=>{
        console.log("Increased");
        alertify.success("Successfully Done");
        if (u.taskStatus == 3) {

          this.msg = u.toDo + " is completed";
          let object: Mail = {
            "recipient": window.localStorage.getItem("email"),
            "msgBody": this.msg,
            "subject": "Your Task is completed"
          }
          this.http.post<any>("http://localhost:9090/sendMail", object)
            .subscribe(
              (success: any) => {
                // console.log(success);
                alertify.success("Notification Sent");
              }
            );
        }
        // this.listservice.getToDoList();
        // this.Refresh();
        // this.ngOnInit();
        this.router.navigate(['/home']);
      },error=>{
        console.log("Not Increased");
        alertify.error("Not Forwarded")
        
      }
    )
  }
  moveBackward(u:any){
    this.kanbanservice.decreaseTaskStatus(u).subscribe(
      success=>{
        console.log("Decreased");
        alertify.success("Successfully Done");
        // this.ngOnInit();
        // this.Refresh();
        this.router.navigate(['/home']);
      },error=>{
        console.log("Not Decreased");
        alert("Not decre.")
      }
    )
  }

  logOut(){
    this.loginservices.logout();
    alertify.success("Logged Out Successfully..");
  }
  
  changeTaskStatus(u:Kanban,status:number) {
    this.kanbanservice.changeTaskStatus(u,status).subscribe(
      success => {
        console.log("Changed");
        alertify.success("Successfully Changed");
        // this.ngOnInit();
        // this.Refresh();
        this.router.navigate(['/home']);
      }, error => {
        console.log("Not changed");
        alertify.error("Not Changed")
      }
    )
  }

  onTaskSelected(val:any){
    // console.log(u);
    this.task=val;
    console.log(this.task);

    // this.changeTaskStatus(u,val)
  }

  getTask(u:Kanban){
    // this.changeTaskStatus(u,this.task);
    this.kanbanservice.changeTaskStatus(u,this.task).subscribe(
      success => {
        console.log("Changed");
        alertify.success("Successfully Changed");
        // this.ngOnInit();
        // this.Refresh();
        this.router.navigate(['/home']);
      }, error => {
        console.log("Not changed");
        alertify.error("Not Changed")
      }
    )
  }

}
