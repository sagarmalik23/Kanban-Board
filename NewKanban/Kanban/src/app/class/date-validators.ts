import { AbstractControl } from "@angular/forms";
export function dateValidators(control:AbstractControl){
    const currentDate=new Date();
    // DateFormat const dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
    // window.localStorage.setItem('date',currentDate);
    // String strDate = dateFormat.format(currentDate); 
    const startdate=new Date(control.value)
    if(startdate<currentDate)
    {
        return{invalidDate:true};
    }
    return null;
}               ``