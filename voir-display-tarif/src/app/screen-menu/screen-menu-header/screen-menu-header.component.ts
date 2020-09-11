import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-screen-menu-header',
  templateUrl: './screen-menu-header.component.html',
  styleUrls: ['./screen-menu-header.component.css']
})
export class ScreenMenuHeaderComponent implements OnInit {

  @Input() title: string;
  @Input() hasGeant: string;

  displayGeant: boolean;

  constructor() { }

  ngOnInit() {
    this.displayGeant = this.hasGeant === 'true';
  }


}
