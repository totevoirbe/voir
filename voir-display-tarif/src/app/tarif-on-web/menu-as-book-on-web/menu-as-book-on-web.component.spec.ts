import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuAsBookOnWebComponent } from './menu-as-book-on-web.component';

describe('MenuAsBookOnWebComponent', () => {
  let component: MenuAsBookOnWebComponent;
  let fixture: ComponentFixture<MenuAsBookOnWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuAsBookOnWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuAsBookOnWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
