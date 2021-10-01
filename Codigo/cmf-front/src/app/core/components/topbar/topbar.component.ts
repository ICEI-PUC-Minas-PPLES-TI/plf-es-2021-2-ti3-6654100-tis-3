import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { tap } from 'rxjs/operators';
import { TopbarService } from 'src/app/services/topbar.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss'],
})
export class TopbarComponent implements OnInit, OnDestroy {


  @Input() topbarTitle: string;

  subscriptions: Subscription[] = [];

  backButtonOpt: {
    enable: boolean;
    path?: string;
  } = {
    enable: false,
    path: '',
  };


  constructor(
    private topbarService: TopbarService,
  ) { }

  ngOnInit() {
    const obs = this.topbarService.backButton$.pipe(
      tap(async (opts: { enable: boolean; path?: string}) => {
        this.backButtonOpt = opts;
      })
    ).subscribe();

    this.subscriptions.push(obs);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(e => {
      e.unsubscribe();
    });
  }

}
