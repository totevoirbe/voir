import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-tabs-menu-header',
  templateUrl: './tabs-menu-header.component.html',
  styleUrls: ['./tabs-menu-header.component.css']
})
export class TabsMenuHeaderComponent implements OnInit {

  @Input() title: string;
  @Input() hasGeant: string;

  displayGeant: boolean;

  constructor() { }

  ngOnInit() {
    this.displayGeant = this.hasGeant === 'true';
  }


}
