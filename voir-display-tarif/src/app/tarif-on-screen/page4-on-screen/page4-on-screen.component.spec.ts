import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Page4OnScreenComponent } from './page4-on-screen.component';

describe('Page4OnScreenComponent', () => {
  let component: Page4OnScreenComponent;
  let fixture: ComponentFixture<Page4OnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Page4OnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Page4OnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
