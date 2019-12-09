import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RowProducttitleOnScreenComponent } from './row-producttitle-on-screen.component';

describe('RowProducttitleOnScreenComponent', () => {
  let component: RowProducttitleOnScreenComponent;
  let fixture: ComponentFixture<RowProducttitleOnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RowProducttitleOnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RowProducttitleOnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
