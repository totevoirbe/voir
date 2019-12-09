import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RowProductOnScreenComponent } from './row-product-on-screen.component';

describe('RowProductOnScreenComponent', () => {
  let component: RowProductOnScreenComponent;
  let fixture: ComponentFixture<RowProductOnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RowProductOnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RowProductOnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
