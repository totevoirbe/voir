import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TarifOnScreenComponent } from './tarif-on-screen.component';

describe('TarifOnScreenComponent', () => {
  let component: TarifOnScreenComponent;
  let fixture: ComponentFixture<TarifOnScreenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarifOnScreenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TarifOnScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
