import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsCard } from './forms-card';

describe('FormsCard', () => {
  let component: FormsCard;
  let fixture: ComponentFixture<FormsCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormsCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormsCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
