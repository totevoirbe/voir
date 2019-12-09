import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Page2OnScreenComponent } from './page2-on-screen.component';

describe('Page2OnScreenComponent', () => {
  let component: Page2OnScreenComponent;
  let fixture: ComponentFixture<Page2OnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Page2OnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Page2OnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
