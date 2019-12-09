import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


/**
 * @title Basic use of `<table mat-table>`
 */

@Component({
  selector: 'app-tarif-on-screen',
  templateUrl: './tarif-on-screen.component.html',
  styleUrls: ['./tarif-on-screen.component.css']
})
export class TarifOnScreenComponent implements OnInit {

  page1: boolean;
  page2: boolean;
  page3: boolean;
  page4: boolean;

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    const pageSelector = this.route.snapshot.paramMap.get('pageSelector');
    if ('P1' === pageSelector) {
      this.page1 = true;
    } else if ('P2' === pageSelector) {
      this.page2 = true;
    } else if ('P3' === pageSelector) {
      this.page3 = true;
    } else if ('P4' === pageSelector) {
      this.page4 = true;
    } else {
      console.error('Illegal page selecture : [' + pageSelector + ']');
    }
  }
}
