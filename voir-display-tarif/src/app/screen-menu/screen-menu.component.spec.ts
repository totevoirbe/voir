import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreenMenuComponent } from './screen-menu.component';

describe('ScreenMenuComponent', () => {
  let component: ScreenMenuComponent;
  let fixture: ComponentFixture<ScreenMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScreenMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScreenMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
