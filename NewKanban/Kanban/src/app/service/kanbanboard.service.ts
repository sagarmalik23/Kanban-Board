import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Kanban } from '../kanban';

@Injectable({
  providedIn: 'root'
})
export class KanbanboardService {
private saveTaskForUserUrl:string="";
private deleteTaskForUserUrl:string="";
private increaseTaskStatusUrl:string="";
private decreaseTaskStatusUrl:string="";
private getAllTaskForUserUrl:string='';
private priorityChangeUrl:string="";
private updateUserUrl:string="";
private changeTaskStatusUrl:string="";

  constructor(private httpclient:HttpClient) { 
    this.saveTaskForUserUrl="http://localhost:65001/userBoard/task/addTask/";
    this.deleteTaskForUserUrl="http://localhost:65001/userBoard/task/deleteTask/";
    this.increaseTaskStatusUrl="http://localhost:65001/userBoard/task/increaseStatus/";
    this.decreaseTaskStatusUrl="http://localhost:65001/userBoard/task/decreaseStatus/";
    this.getAllTaskForUserUrl="http://localhost:65001/userBoard/task/getAllTaskForParticularUser/";
    this.priorityChangeUrl="http://localhost:65001/userBoard/task/";
    this.updateUserUrl="http://localhost:65001/userBoard/task/updateUser/";
    this.changeTaskStatusUrl="http://localhost:65001/userBoard/task/changeStatus/"
  }

  saveTaskForUser(data:any){
    // window.localStorage.getItem('email'this.saveTaskForUser.)
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.post<any>(this.saveTaskForUserUrl+Email,data,{ 'headers': reqHeader });
  };

  deleteTaskForUser(data:any,id:number){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.delete<Kanban>(this.deleteTaskForUserUrl+Email+"/"+id,{ 'headers': reqHeader });
  };

  increaseTaskStatus(data:any){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.put<any>(this.increaseTaskStatusUrl+Email,data,{ 'headers': reqHeader })
  };

  decreaseTaskStatus(data:any){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.put<any>(this.decreaseTaskStatusUrl+Email,data,{ 'headers': reqHeader })
  };

  getAllTaskForUser(){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.get<any>(this.getAllTaskForUserUrl+Email,{ 'headers': reqHeader })
  };

  priorityChange(data:any){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.put<any>(this.priorityChangeUrl+Email,data,{ 'headers': reqHeader })
  };

  updateUserTask(data:any,taskId:any){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.put<any>(this.updateUserUrl+Email+"/"+taskId,data,{ 'headers': reqHeader })
  };

  changeTaskStatus(data:any,status:any){
    let Email = window.localStorage.getItem('email');
    let reqHeader = new HttpHeaders().set('Authorization','Bearer ' + window.localStorage.getItem('token'));
    return this.httpclient.put<any>(this.changeTaskStatusUrl+Email+"/"+status,data,{ 'headers': reqHeader })
  };


}
