import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-row-producttitle-on-screen',
  templateUrl: './row-producttitle-on-screen.component.html',
  styleUrls: ['./row-producttitle-on-screen.component.css']
})

export class RowProducttitleOnScreenComponent implements OnInit {

  @Input() title: string;
  @Input() hasGeant: string;

  displayGeant: boolean;

  constructor() { }

  ngOnInit() {
    this.displayGeant = this.hasGeant === 'true';
  }
}
