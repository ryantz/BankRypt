import { Component } from '@angular/core';

/*
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
*/

@Component({
  selector: 'app-root', // name to ref to in other templates
  template: `
  @if (isServerRunning){
    Hello server is running
  }
  @else {
   the app {{title}} is under construction, server is not running 
  }
  `,
  styles: `
  :host {
    color: black
  }`,
})
export class AppComponent {
  // component properties
  title = 'bankrypt-fe';
  isServerRunning = true;
}

@Component ({
  selector: 'app-user',
  template: `<section><app-user /></section>`,
  imports: [UserComponent],
})
export class UserComponent {
}