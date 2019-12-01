import { Component, EventEmitter, Input, Output, OnDestroy, OnInit } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { repos } from '../repos';

@Component({
  selector: 'app-carousel-photo',
  templateUrl: 'carousel-photo.component.html',
  styleUrls: ['./carousel-photo.component.css'],
  animations: [
    trigger('thumbState', [
      state('inactive', style({
        opacity: 0, transform: 'scale(0.5)'
      })),
      state('active', style({
        opacity: 1, transform: 'scale(1)'
      })),
      // cubic-bezier from http://easings.net/
      transition('inactive => active', animate('500ms cubic-bezier(0.785, 0.135, 0.15, 0.86)')),
      transition('active => inactive', animate('500ms cubic-bezier(0.785, 0.135, 0.15, 0.86)'))
    ])
  ]
})

export class CarouselPhotoComponent implements OnInit, OnDestroy {
  @Input() images: Array<string>;
  @Output() change: EventEmitter<number> = new EventEmitter<number>();


  counter = 0;
  currentInterval;
  autoPlayDuration = 5000;
  imageNumber = 0;

  repos: repos[];
  loading: boolean = false;
  errorMessage;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

    this.getRepos()
      .subscribe((response) => { this.repos = response; console.log('repos : ' + this.repos); },
        (error) => { this.errorMessage = error; console.log(this.errorMessage); this.errorMessage = false; },
        () => { this.loading = false; });

    if (this.images) {
      if (this.images.length > 1 && this.autoPlayDuration > 0) {
        this.startTimer();
      }
    }
  }

  getRepos(): Observable<repos[]> {
    this.loading = true;
    this.errorMessage = '';
    return this.http.get<repos[]>('../');
  }


  ngAfterContentInit() {
    this.change.emit(0);
  }

  onClickThumb(event) {
    const total = this.images.length - 1;
    this.counter = event.layerX < 150 ? this.dec(total) : this.inc(total);
    this.change.emit(this.counter);
  }

  inc(total) {
    return (this.counter < total) ? this.counter + 1 : 0;
  }

  dec(total) {
    return (this.counter > 0) ? this.counter - 1 : total;
  }


  select(): void {
    this.resetTimer();
    this.counter = this.counter + 1;
    if (this.counter >= this.images.length - 1) {
      this.counter = 0;
    }
    this.change.emit(this.counter);
    this.startTimer();
  }

  startTimer(): void {
    this.resetTimer();
    if (this.autoPlayDuration > 0) {
      this.currentInterval = setInterval(() => this.select(), this.autoPlayDuration);
    }
  }

  resetTimer(): void {
    if (this.currentInterval) {
      clearInterval(this.currentInterval);
    }
  }


  ngOnDestroy(): void {
    this.resetTimer();
  }

}