import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabsMenuProductComponent } from './tabs-menu-product.component';

describe('TabsMenuProductComponent', () => {
  let component: TabsMenuProductComponent;
  let fixture: ComponentFixture<TabsMenuProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabsMenuProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabsMenuProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
