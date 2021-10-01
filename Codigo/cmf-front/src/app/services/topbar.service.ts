import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopbarService {

  backButton$: BehaviorSubject<{
    enable: boolean;
    path?: string;
  }> = new BehaviorSubject({enable: false});

  constructor() { }

  configBackButton(enable: boolean, path?: string) {
    this.backButton$.next({ enable, path});
  }
}
