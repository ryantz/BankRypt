import { Component } from '@angular/core';

/*
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
*/

@Component({
  selector: 'app-root',
  template: `Hello, the {{title}} is under construction`,
  styles: `
  :host {
    color: black
  }`,

})
export class AppComponent {
  title = 'bankrypt-fe';
}

/*
@Component ({
  selector: 

})
  */
export class UserComponent {

}


