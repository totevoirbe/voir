import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Page3OnScreenComponent } from './page3-on-screen.component';

describe('Page3OnScreenComponent', () => {
  let component: Page3OnScreenComponent;
  let fixture: ComponentFixture<Page3OnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Page3OnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Page3OnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
