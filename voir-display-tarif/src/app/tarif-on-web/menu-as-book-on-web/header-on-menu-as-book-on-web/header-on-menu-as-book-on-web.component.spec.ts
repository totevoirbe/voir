import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderOnMenuAsBookOnWebComponent } from './header-on-menu-as-book-on-web.component';

describe('HeaderOnMenuAsBookOnWebComponent', () => {
  let component: HeaderOnMenuAsBookOnWebComponent;
  let fixture: ComponentFixture<HeaderOnMenuAsBookOnWebComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderOnMenuAsBookOnWebComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderOnMenuAsBookOnWebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
