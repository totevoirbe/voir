import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-tabs-menu',
  templateUrl: './tabs-menu.component.html',
  styleUrls: ['./tabs-menu.component.css']
})
export class TabsMenuComponent implements OnInit {

  public selected = new FormControl(0);
  public tabIndex = 0;

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

    //  setInterval(this.scheduledActivity, 5000, this);

  }

  scheduledActivity(tabsMenuComponent: TabsMenuComponent): void {
    if (tabsMenuComponent.tabIndex > 4) {
      tabsMenuComponent.tabIndex = 0;
    }
    tabsMenuComponent.selected.setValue(tabsMenuComponent.tabIndex++);
  }

}
