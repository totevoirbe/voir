import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Page1OnScreenComponent } from './page1-on-screen.component';

describe('Page1OnScreenComponent', () => {
  let component: Page1OnScreenComponent;
  let fixture: ComponentFixture<Page1OnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Page1OnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Page1OnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
