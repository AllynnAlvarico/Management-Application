import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Backicon } from './backicon';

describe('Backicon', () => {
  let component: Backicon;
  let fixture: ComponentFixture<Backicon>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Backicon]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Backicon);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
