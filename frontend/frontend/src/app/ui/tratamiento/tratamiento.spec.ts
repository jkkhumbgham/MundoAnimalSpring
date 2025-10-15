import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TratamientoComponent} from './tratamiento';

describe('Tratamiento', () => {
  let component: TratamientoComponent;
  let fixture: ComponentFixture<TratamientoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TratamientoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TratamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
