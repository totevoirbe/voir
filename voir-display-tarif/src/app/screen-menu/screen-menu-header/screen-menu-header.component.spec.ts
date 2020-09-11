import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreenMenuHeaderComponent } from './screen-menu-header.component';

describe('ScreenMenuHeaderComponent', () => {
  let component: ScreenMenuHeaderComponent;
  let fixture: ComponentFixture<ScreenMenuHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScreenMenuHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScreenMenuHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
