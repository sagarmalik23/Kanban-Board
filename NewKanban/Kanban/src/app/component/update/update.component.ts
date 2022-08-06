import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { dateValidators } from 'src/app/class/date-validators';
import { KanbanboardService } from 'src/app/service/kanbanboard.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

    task:any;

    UpdateTask=new FormGroup({
    taskId:new FormControl(window.localStorage.getItem('taskId')),
    toDo:new FormControl(window.localStorage.getItem('todo'),[Validators.required]),
    dateAssign:new FormControl((new Date()).toISOString().substring(0,10)),
    deadline:new FormControl(window.localStorage.getItem('deadline'),[Validators.required,dateValidators]),
    priority:new FormControl(window.localStorage.getItem('priority'))
  });


  todayDate=(new Date()).toISOString().substring(0,10);
  
  constructor(private kanbanService:KanbanboardService, private route:ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
  }

  updateTask(){
    this.task=this.UpdateTask.value;
    console.log(window.localStorage.getItem('taskId'));
    console.log(this.UpdateTask.value);
    console.log(this.task)
    this.kanbanService.updateUserTask(this.task,window.localStorage.getItem('taskId')).subscribe(
      response=>{
        console.log("hello"+response);
        this.router.navigate(['/home']);
      },error=>{
          console.log("error "+error)
      }
    )
  }

  get taskId(){
    return this.UpdateTask.get('taskId')
   }
   get toDo(){
    return this.UpdateTask.get('toDo')
   }
   get dateAssign(){
    return this.UpdateTask.get('dateAssign')
   }
   get deadline(){
    return this.UpdateTask.get('deadline')
   }
   get priority(){
    return this.UpdateTask.get('priority')
   }

}
