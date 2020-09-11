import { Component, OnInit } from '@angular/core';
import { Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-screen-menu',
  templateUrl: './screen-menu.component.html',
  styleUrls: ['./screen-menu.component.css']
})

export class ScreenMenuComponent implements OnInit {

  constructor(private meta: Meta) {
    this.meta.addTags([
      { name: 'Author', content: 'Panidel sprl' },
      { name: 'Copyright', content: 'Panidel sprl' },
      { name: 'Description', content: 'Sandwicherie dagobert arlon, restauration rapide de qualit & eacute; à juste prix' },
      { name: 'Expires', content: 'never' },
      { name: 'Generator', content: 'SelfPanidel' },
      { name: 'Keywords', content: 'Sandwich, dagobert, arlon, salade, panini, rapide,& eacute; quilibr & eacute;, p & acirc; te, kebab' },
      { name: 'Rev', content: 'webmaster@dagoarlon.be' },
      { name: 'Revisit - after', content: '30' },
      { name: 'Robots', content: 'all' },
      { name: 'Subject', content: 'Attribut' },
      { name: 'google - site - verification', content: 'u1sBXq6N2ywMSIqiSqOmtXaksQZUyaOIUXHT3 - U3ZmE' }
    ]);
  }

  ngOnInit() {
  }

}
