import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavBarService {

  visible: boolean;
  constructor() {
    this.hide();
  }

  hide() {
    this.visible = false;
  }

  show() {
    this.visible = true;
  }
}
