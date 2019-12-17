import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-header-on-menu-as-book-on-web',
  templateUrl: './header-on-menu-as-book-on-web.component.html',
  styleUrls: ['./header-on-menu-as-book-on-web.component.css']
})
export class HeaderOnMenuAsBookOnWebComponent implements OnInit {

  @Input() title: string;
  @Input() hasGeant: string;

  displayGeant: boolean;

  constructor() { }

  ngOnInit() {
    this.displayGeant = this.hasGeant === 'true';
  }


}
